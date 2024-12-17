package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Plato;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.model.Categoria;
import com.deso.etapa_final.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VendedorService vendedorService;

    public Plato createPlato(String nombre, String descripcion, float precio, long id_vendedor,  Iterable<String> categorias, float calorias, boolean aptoCeliaco, boolean aptoVegano , float peso) {
        Plato plato = new Plato();
        Vendedor vendedor = vendedorService.getVendedorById(id_vendedor);
        plato.setNombre(nombre);
        plato.setDescripcion(descripcion);
        plato.setPrecio(precio);
        plato.setVendedor(vendedor);
        plato.setEs_bebida(false);
        plato.setPeso(peso);
        plato.setCalorias(calorias);
        if (categorias != null) {
            for (String cat : categorias) {
                plato.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.PLATO));
            }
        }
        plato.setAptoCeliaco(aptoCeliaco);
        plato.setAptoVegano(aptoVegano);
        return platoRepository.save(plato);
    }
    public Iterable<Plato> getPlatosByVendedorId(Long id) {
        return platoRepository.findByVendedor_Vendedorid(id);
    }
    public Plato getPlatoById(Long id) {
        return platoRepository.findById(id).orElse(null);
    }
    public Iterable<Plato> generalSearch(String searchable, String orderBy, String orderDirection){
        if(orderBy.equals("precio_mayor_que") || orderBy.equals("precio_menor_que")){
            try{
            Double precio = Double.parseDouble(searchable);
            List <Plato> resultList = new ArrayList<>();
            if(orderBy.equals("precio_menor_que"))
                resultList.addAll(platoRepository.findByPrecioLessThanEqual(precio));
            else
                resultList.addAll(platoRepository.findByPrecioGreaterThanEqual(precio));
            resultList.sort((p1, p2) -> {
                int comparison = 0;
                if (p1.getPrecio() > p2.getPrecio()) {
                    comparison = 1;
                } else if (p1.getPrecio() < p2.getPrecio()) {
                    comparison = -1;
                }
                if(orderDirection.equals("DESC"))
                    comparison = comparison * -1;
                return comparison;
            });
            return resultList;
        }catch(NumberFormatException e){return new ArrayList<>();}
        }else if(orderBy.equals("peso_mayor_que") || orderBy.equals("peso_menor_que")){
            try{
            Double peso = Double.parseDouble(searchable);
            List <Plato> resultList = new ArrayList<>();
            if(orderBy.equals("peso_menor_que"))
                resultList.addAll(platoRepository.findByPesoLessThanEqual(peso));
            else
                resultList.addAll(platoRepository.findByPesoGreaterThanEqual(peso));
            resultList.sort((p1, p2) -> {
                int comparison = 0;
                if (p1.getPeso() > p2.getPeso()) {
                    comparison = 1;
                } else if (p1.getPeso() < p2.getPeso()) {
                    comparison = -1;
                }
                if(orderDirection.equals("DESC"))
                    comparison = comparison * -1;
                return comparison;
            });
            return resultList;
        }catch(NumberFormatException e){return new ArrayList<>();}
    }else if(orderBy.equals("calorias_mayor_que")|| orderBy.equals("calorias_menor_que")){
        try{
        Double calorias = Double.parseDouble(searchable);
        List <Plato> resultList = new ArrayList<>();
        if(orderBy.equals("calorias_menor_que"))
            resultList.addAll(platoRepository.findByCaloriasLessThanEqual(calorias));
        else
            resultList.addAll(platoRepository.findByCaloriasGreaterThanEqual(calorias));
        resultList.sort((p1, p2) -> {
            int comparison = 0;
            if (p1.getCalorias() > p2.getCalorias()) {
                comparison = 1;
            } else if (p1.getCalorias() < p2.getCalorias()) {
                comparison = -1;
            }
            if(orderDirection.equals("DESC"))
                comparison = comparison * -1;
            return comparison;
        });
        return resultList;
    }catch(NumberFormatException e){return new ArrayList<>();}
    }else if(orderBy.equals("aptoCeliaco")){
        List<Plato> resultList = new ArrayList<>();
        resultList.addAll(platoRepository.findByAptoCeliaco(true));
        return resultList;
    }else if(orderBy.equals("aptoVegano")){
        List<Plato> resultList = new ArrayList<>();
        resultList.addAll(platoRepository.findByAptoVegano(true));
        return resultList;
    }
    HashSet<Plato> resultSet = new HashSet<>();
    resultSet.addAll(platoRepository.findByNombreContaining(searchable));
    resultSet.addAll(platoRepository.findByDescripcionContaining(searchable));
    resultSet.addAll(platoRepository.findByVendedor_nombre(searchable));
    List<Plato> byNombreCategoria = platoRepository.findByCategorias_nombreContaining(searchable);
    resultSet.addAll(byNombreCategoria);
    try {
        Long id = Long.parseLong(searchable);
        Plato byId = platoRepository.findById(id).orElse(null);
        if(byId != null) resultSet.add(byId);

        List<Plato> byVendedorId = platoRepository.findByVendedor_Vendedorid(id);
        resultSet.addAll(byVendedorId);
    }catch(NumberFormatException e){}


    List<Plato> resultList = new ArrayList<>(resultSet);
    resultList.sort((p1, p2) -> {
        int comparison = 0;
        switch (orderBy) {
            case "nombre":
                comparison = p1.getNombre().compareTo(p2.getNombre());
                break;
            case "id":
                comparison = p1.getItemid().compareTo(p2.getItemid());
                break;
            case "vendedor":
                comparison = p1.getVendedor().getNombre().compareTo(p2.getVendedor().getNombre());
                break;
            case "precio":
                comparison = Double.compare(p1.getPrecio(), p2.getPrecio());
                break;
            case "calorias":
                comparison = Double.compare(p1.getCalorias(), p2.getCalorias());
                break;
            case "peso":
                comparison = Double.compare(p1.getPeso(), p2.getPeso());
                break;
            default:
                comparison = p1.getItemid().compareTo(p2.getItemid());
                break;
            }
            if(orderDirection.equals("DESC")) {
                comparison = comparison * -1;
            }
            return comparison;
        });
        return resultList;
        }
        public Iterable<Plato> generalSearchByVendedor(String searchable, String orderBy, String orderDirection, Long idVendedor){
            if(orderBy.equals("precio_mayor_que") || orderBy.equals("precio_menor_que")){
                try{
                Double precio = Double.parseDouble(searchable);
                List <Plato> resultList = new ArrayList<>();
                if(orderBy.equals("precio_menor_que"))
                    resultList.addAll(platoRepository.findByPrecioLessThanEqualAndVendedor_vendedorid(precio, idVendedor));
                else
                    resultList.addAll(platoRepository.findByPrecioGreaterThanEqualAndVendedor_vendedorid(precio, idVendedor));
                resultList.sort((p1, p2) -> {
                    int comparison = 0;
                    if (p1.getPrecio() > p2.getPrecio()) {
                        comparison = 1;
                    } else if (p1.getPrecio() < p2.getPrecio()) {
                        comparison = -1;
                    }
                    if(orderDirection.equals("DESC"))
                        comparison = comparison * -1;
                    return comparison;
                });
                return resultList;
            }catch(NumberFormatException e){return new ArrayList<>();}
            }else if(orderBy.equals("peso_mayor_que") || orderBy.equals("peso_menor_que")){
                try{
                Double peso = Double.parseDouble(searchable);
                List <Plato> resultList = new ArrayList<>();
                if(orderBy.equals("peso_menor_que"))
                    resultList.addAll(platoRepository.findByPesoLessThanEqualAndVendedor_vendedorid(peso, idVendedor));
                else
                    resultList.addAll(platoRepository.findByPesoGreaterThanEqualAndVendedor_vendedorid(peso, idVendedor));
                resultList.sort((p1, p2) -> {
                    int comparison = 0;
                    if (p1.getPeso() > p2.getPeso()) {
                        comparison = 1;
                    } else if (p1.getPeso() < p2.getPeso()) {
                        comparison = -1;
                    }
                    if(orderDirection.equals("DESC"))
                        comparison = comparison * -1;
                    return comparison;
                });
                return resultList;
            }catch(NumberFormatException e){return new ArrayList<>();}
        }else if(orderBy.equals("calorias_mayor_que")|| orderBy.equals("calorias_menor_que")){
            try{
            Double calorias = Double.parseDouble(searchable);
            List <Plato> resultList = new ArrayList<>();
            if(orderBy.equals("calorias_menor_que"))
                resultList.addAll(platoRepository.findByCaloriasLessThanEqualAndVendedor_vendedorid(calorias, idVendedor));
            else
                resultList.addAll(platoRepository.findByCaloriasGreaterThanEqualAndVendedor_vendedorid(calorias, idVendedor));
            resultList.sort((p1, p2) -> {
                int comparison = 0;
                if (p1.getCalorias() > p2.getCalorias()) {
                    comparison = 1;
                } else if (p1.getCalorias() < p2.getCalorias()) {
                    comparison = -1;
                }
                if(orderDirection.equals("DESC"))
                    comparison = comparison * -1;
                return comparison;
            });
            return resultList;
        }catch(NumberFormatException e){return new ArrayList<>();}
        }else if(orderBy.equals("aptoCeliaco")){
            List<Plato> resultList = new ArrayList<>();
            resultList.addAll(platoRepository.findByAptoCeliacoAndVendedor_vendedorid(true, idVendedor));
            return resultList;
        }else if(orderBy.equals("aptoVegano")){
            List<Plato> resultList = new ArrayList<>();
            resultList.addAll(platoRepository.findByAptoVeganoAndVendedor_vendedorid(true, idVendedor));
            return resultList;
        }
        HashSet<Plato> resultSet = new HashSet<>();
        resultSet.addAll(platoRepository.findByNombreContainingAndVendedor_vendedorid(searchable, idVendedor));
        resultSet.addAll(platoRepository.findByDescripcionContainingAndVendedor_vendedorid(searchable, idVendedor));
        List<Plato> byNombreCategoria = platoRepository.findByCategorias_nombreContainingAndVendedor_vendedorid(searchable, idVendedor);
        resultSet.addAll(byNombreCategoria);
        try {
            Long id = Long.parseLong(searchable);
            Plato byId = platoRepository.findByitemidAndVendedor_vendedorid(id, idVendedor).orElse(null);
            if(byId != null) resultSet.add(byId);
        }catch(NumberFormatException e){}
    
    
        List<Plato> resultList = new ArrayList<>(resultSet);
        resultList.sort((p1, p2) -> {
            int comparison = 0;
            switch (orderBy) {
                case "nombre":
                    comparison = p1.getNombre().compareTo(p2.getNombre());
                    break;
                case "id":
                    comparison = p1.getItemid().compareTo(p2.getItemid());
                    break;
                case "precio":
                    comparison = Double.compare(p1.getPrecio(), p2.getPrecio());
                    break;
                case "calorias":
                    comparison = Double.compare(p1.getCalorias(), p2.getCalorias());
                    break;
                case "peso":
                    comparison = Double.compare(p1.getPeso(), p2.getPeso());
                    break;
                default:
                    comparison = p1.getItemid().compareTo(p2.getItemid());
                    break;
                }
                if(orderDirection.equals("DESC")) {
                    comparison = comparison * -1;
                }
                return comparison;
            });
            return resultList;
        }

    public List<Plato> getAllPlatos() {
        return (List<Plato>) platoRepository.findAll();
    }



    public Plato updatePlato(Long id, String nombre, String descripcion, float precio, float peso, float tamaño, Iterable<String> categorias) {
        Plato plato = platoRepository.findById(id).orElse(null);
        if (plato != null) {
            plato.setNombre(nombre);
            plato.setDescripcion(descripcion);
            plato.setPrecio(precio);
            plato.setPeso(peso);
            if (categorias != null) {
                plato.addCategoria(categoriaService.getCategoriaByNombre("Plato", Categoria.TipoCategoria.PLATO));
                for (String cat : categorias) {
                    plato.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.PLATO));
                }
            }
            return platoRepository.save(plato);
        }
        return null;
    }

    public void deletePlato(Long id) {
        platoRepository.deleteById(id);
    }
}