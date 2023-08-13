package br.com.estudos.edusocrates.pagamentos.service.impl;

import br.com.estudos.edusocrates.pagamentos.model.DTO.PagamentoDTO;
import br.com.estudos.edusocrates.pagamentos.model.Pagamento;
import br.com.estudos.edusocrates.pagamentos.model.Status;
import br.com.estudos.edusocrates.pagamentos.repository.PagamentoRepository;
import br.com.estudos.edusocrates.pagamentos.service.PagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
        return pagamentoRepository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDTO.class));
    }

    @Override
    public PagamentoDTO obterPorId(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    @Override
    public PagamentoDTO criarPagamento(PagamentoDTO dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    @Override
    public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = pagamentoRepository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    @Override
    public void excluirPagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }


}
