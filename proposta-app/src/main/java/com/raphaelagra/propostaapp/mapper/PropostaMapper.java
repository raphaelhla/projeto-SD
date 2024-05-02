package com.raphaelagra.propostaapp.mapper;

import java.text.NumberFormat;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.raphaelagra.propostaapp.dto.PropostaRequestDto;
import com.raphaelagra.propostaapp.dto.PropostaResponseDto;
import com.raphaelagra.propostaapp.entity.Proposta;

@Mapper
public interface PropostaMapper {

	PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);
	
	@Mapping(target = "usuario.nome", source = "nome")
	@Mapping(target = "usuario.sobrenome", source = "sobrenome")
	@Mapping(target = "usuario.cpf", source = "cpf")
	@Mapping(target = "usuario.telefone", source = "telefone")
	@Mapping(target = "usuario.renda", source = "renda")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "aprovada", ignore = true)
	@Mapping(target = "integrada", constant = "true")
	@Mapping(target = "observacao", ignore = true)
	Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);
	
	@Mapping(target = "nome", source = "usuario.nome")
	@Mapping(target = "sobrenome", source = "usuario.sobrenome")
	@Mapping(target = "cpf", source = "usuario.cpf")
	@Mapping(target = "telefone", source = "usuario.telefone")
	@Mapping(target = "renda", source = "usuario.renda")
	@Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
	PropostaResponseDto convertEntityToDto(Proposta proposta);
	
	List<PropostaResponseDto> convertListEntityToListDto(Iterable<Proposta> propostas);
	
	default String setValorSolicitadoFmt(Proposta proposta) {
		return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
	}
}
