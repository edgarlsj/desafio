package com.bootcamp.services;

import com.bootcamp.dto.MunicipioDTO;
import com.bootcamp.entities.Municipio;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.repositories.MunicipioRepository;
import com.bootcamp.mapper.MunicipioMapper;
import com.bootcamp.repositories.UFRepository;
import com.bootcamp.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;
    @Autowired
    private MunicipioMapper municipioMapper;
    @Autowired
    private UFRepository UFRepository;

    public List<MunicipioDTO> getAll(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        List<Municipio> municipios = municipioRepository.findAll().stream()
                .filter(m -> (codigoMunicipio == null || m.getCodigoMunicipio().equals(codigoMunicipio)) &&
                        (codigoUF == null || m.getUf().getCodigoUF().equals(codigoUF)) &&
                        (nome == null || m.getNome().equalsIgnoreCase(nome)) &&
                        (status == null || m.getStatus() == status))
                .collect(Collectors.toList());
        return municipios.stream()
                .map(municipioMapper::toDTO)
                .collect(Collectors.toList());

    }

    public MunicipioDTO getByCodigoMunicipio(Long codigoMunicipio) {
        Municipio municipio = municipioRepository.findById(codigoMunicipio).orElseThrow(() -> new DesafioException("Não foi possível consultar municipio no banco de dados."));
        return municipioMapper.toDTO(municipio);
    }
    @Transactional
    public List<MunicipioDTO> createMunicipio(MunicipioDTO municipioDTO) {
//        Validações
        ValidateUtils.validateStatus(municipioDTO.getStatus());
        ValidateUtils.validateNome(municipioDTO.getNome());

      //Valida se já existe um registro com o mesmo nome ou sigla
        if (municipioRepository.existsByNome(municipioDTO.getNome())) {
            throw new DesafioException("Não foi possível incluir municipio no banco de dados. Motivo: já existe um(a) registro com o nome no banco de dados.");
        }
        if (!UFRepository.existsByCodigoUF(municipioDTO.getCodigoUF())) {
            throw new DesafioException("Não foi possível incluir municipio no banco de dados. Motivo: código do UF não encontrado.");
        }


        Municipio municipio = municipioMapper.toEntity(municipioDTO);
         municipioRepository.save(municipio);
         List<MunicipioDTO> municipios = getAll(null, null, null, null);//retona todos os municipios
        return municipios;
    }

    @Transactional
    public List<MunicipioDTO> updateMunicipio(MunicipioDTO municipioDTO) {
        Municipio municipio = municipioMapper.toEntity(municipioDTO);
        municipioRepository.save(municipio);
        List<MunicipioDTO> municipios = getAll(null, null, null, null);//retona todos os municipios
        return municipios;
    }



}
