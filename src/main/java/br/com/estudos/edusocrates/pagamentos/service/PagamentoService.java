package br.com.estudos.edusocrates.pagamentos.service;


import br.com.estudos.edusocrates.pagamentos.model.DTO.PagamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagamentoService {

    Page<PagamentoDTO> obterTodos(Pageable paginacao);

    PagamentoDTO obterPorId(Long id);

    PagamentoDTO criarPagamento(PagamentoDTO dto);

    PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto);

    void excluirPagamento(Long id);
}
