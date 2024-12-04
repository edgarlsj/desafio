package com.bootcamp.services;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.entities.Bairro;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.repositories.BairroRepository;
import com.bootcamp.mapper.BairroMapper;
import com.bootcamp.repositories.MunicipioRepository;
import com.bootcamp.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {
    @Autowired
    public BairroRepository bairroRepository;

    @Autowired
    public BairroMapper bairroMapper;

    @Autowired
    public MunicipioRepository municipioRepository;



        public List<BairroDTO> getAll(Long codigoMunicipio, String nome, Integer status) {
            List<Bairro> bairros = bairroRepository.findAll().stream()
                    .filter(b -> (codigoMunicipio == null || b.getMunicipio().getCodigoMunicipio().equals(codigoMunicipio)) &&
                            (nome == null || b.getNome().equalsIgnoreCase(nome)) &&
                            (status == null || b.getStatus() == status))
                    .collect(Collectors.toList());
            return bairros.stream()
                    .map(bairroMapper::toDto)
                    .collect(Collectors.toList());

    }

    @Transactional
    //Metodo para buscar bairro por codigo
    public Object getByCodigoBairro(Long codigoBairro) {
        Bairro bairro = bairroRepository.findById(codigoBairro).orElse(null);
        if (bairro == null) {
            return new ArrayList<>();
        }
        return bairroMapper.toDto(bairro);
    }

   @Transactional
    //Metodo para buscar bairro por codigo do municipio
    public Object getByCodigoMunicipio(Long codigoMunicipio) {
        List<Bairro> bairros = bairroRepository.findAll().stream()
                .filter(b -> b.getMunicipio().getCodigoMunicipio().equals(codigoMunicipio))
                .collect(Collectors.toList());
        if (bairros.isEmpty()) {
            return new ArrayList<>();
        }
        return bairros.stream()
                .map(bairroMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BairroDTO> createBairro(BairroDTO bairroDTO) {
        ValidateUtils.validateNome(bairroDTO.getNome());
        ValidateUtils.validateStatus(bairroDTO.getStatus());

        bairroDTO.setNome(bairroDTO.getNome().trim()); // Remove espaços do nome
   //Valida se já existe um registro com o mesmo código do bairro e do município informado
        if (!municipioRepository.existsByCodigoMunicipio(bairroDTO.getCodigoMunicipio())) {
            throw new DesafioException("Não foi possível incluir bairro no banco de dados. Motivo: código do município "+bairroDTO.getCodigoMunicipio()+" não encontrado.");
        }

        if (bairroRepository.existsByNomeAndMunicipio_CodigoMunicipio(bairroDTO.getNome(), bairroDTO.getCodigoMunicipio())) {
            throw new DesafioException("Não foi possível incluir bairro no banco de dados. Motivo: bairro com nome "+bairroDTO.getNome()+" e código do município "+bairroDTO.getCodigoMunicipio()+" já existe.");
        }




//        Converte o DTO para entidade
        Bairro bairro = bairroMapper.toEntity(bairroDTO);
        bairroRepository.save(bairro);
        List<BairroDTO> bairrosDTO = getAll(null, null, null);//retona todos os bairros
        return bairrosDTO;
    }

    @Transactional
    public List<BairroDTO> updateBairro(BairroDTO bairroDTO) {
            ValidateUtils.validateNome(bairroDTO.getNome());
            ValidateUtils.validateStatus(bairroDTO.getStatus());

            bairroDTO.setNome(bairroDTO.getNome().trim()); // Remove espaços do nome
        //Valida se já existe um registro com o mesmo código
         if (!bairroRepository.existsByCodigoBairro(bairroDTO.getCodigoBairro())) {
            throw new DesafioException("Não foi possível atualizar bairro no banco de dados. Motivo: código do bairro "+bairroDTO.getCodigoBairro()+" não foi encontrado!.");
        }
         //Valida se já existe um registro com o mesmo nome
        if (!municipioRepository.existsByCodigoMunicipio(bairroDTO.getCodigoMunicipio())) {
            throw new DesafioException("Não foi possível atualizar bairro no banco de dados. Motivo: código do município "+bairroDTO.getCodigoMunicipio()+" não foi encontrado!.");
        }




        Bairro bairro = bairroMapper.toEntity(bairroDTO);
        bairroRepository.save(bairro);
        List<BairroDTO> bairrosDTO = getAll(null, null, null);//retona todos os bairros
        return bairrosDTO;
    }



}
