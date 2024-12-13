package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.repositories.BebidaRepository;
import com.deso.etapa_final.model.Categoria;
import com.deso.etapa_final.model.ItemMenu;
import com.deso.etapa_final.model.Vendedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VendedorService vendedorService;

    public Bebida createBebida(String nombre, String descripcion, float precio, Long id_vendedor, float graduacionAlcoholica, float tamaño, float peso, Iterable<String> categoria) {
        Bebida bebida = new Bebida();
        Vendedor vendedor = vendedorService.getVendedorById(id_vendedor);
        bebida.setNombre(nombre);
        bebida.setDescripcion(descripcion);
        bebida.setPrecio(precio);
        bebida.setVendedor(vendedor);
        bebida.setEs_bebida(true);
        bebida.setGraduacionAlcoholica(graduacionAlcoholica);
        bebida.setTamaño(tamaño);
        bebida.setPeso(peso);
        if(categoria != null) {
            bebida.addCategoria(categoriaService.getCategoriaByNombre("Bebida", Categoria.TipoCategoria.BEBIDA));
            for (String cat : categoria) {
                bebida.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.BEBIDA));
                
            }
        }
        return bebidaRepository.save(bebida);
    }

    public List<Bebida> getAllBebidas() {
        return (List<Bebida>) bebidaRepository.findAll();
    }
    public List<Bebida> generalSearch(String searchable, String orderBy, String orderDirection){
            if(orderBy.equals("precio_mayor_que") || orderBy.equals("precio_menor_que")){
                try{
                Double precio = Double.parseDouble(searchable);
                List <Bebida> resultList = new ArrayList<>();
                if(orderBy.equals("precio_menor_que"))
                    resultList.addAll(bebidaRepository.findByPrecioLessThanEqual(precio));
                else
                    resultList.addAll(bebidaRepository.findByPrecioGreaterThanEqual(precio));
                resultList.sort((p1, p2) -> {
                    int comparison = 0;
                    if (p1.getPrecio() > p2.getPrecio()) {
                        comparison = 1;
                    } else if (p1.getPrecio() < p2.getPrecio()) {
                        comparison = -1;
                    }
                    return comparison;
                });
                return resultList;
            }catch(NumberFormatException e){return new ArrayList<>();}
        }else if(orderBy.equals("graduacion_mayor_que") || orderBy.equals("graduacion_menor_que")){
                try{
                Float graduacion = Float.parseFloat(searchable);
                List <Bebida> resultList = new ArrayList<>();
                if(orderBy.equals("graduacion_menor_que"))
                    resultList.addAll(bebidaRepository.findByGraduacionAlcoholicaLessThanEqual(graduacion));
                else
                    resultList.addAll(bebidaRepository.findByGraduacionAlcoholicaGreaterThanEqual(graduacion));
                resultList.sort((p1, p2) -> {
                    int comparison = 0;
                    if (p1.getGraduacionAlcoholica() > p2.getGraduacionAlcoholica()) {
                        comparison = 1;
                    } else if (p1.getGraduacionAlcoholica() < p2.getGraduacionAlcoholica()) {
                        comparison = -1;
                    }
                    return comparison;
                });
                return resultList;
            }catch(NumberFormatException e){return new ArrayList<>();}
        }else if(orderBy.equals("tamaño_mayor_que") || orderBy.equals("tamaño_menor_que")){
                try{
                Float tamaño = Float.parseFloat(searchable);
                List <Bebida> resultList = new ArrayList<>();
                if(orderBy.equals("tamaño_menor_que"))
                    resultList.addAll(bebidaRepository.findByTamañoLessThanEqual(tamaño));
                else
                    resultList.addAll(bebidaRepository.findByTamañoGreaterThanEqual(tamaño));
                resultList.sort((p1, p2) -> {
                    int comparison = 0;
                    if (p1.getTamaño() > p2.getTamaño()) {
                        comparison = 1;
                    } else if (p1.getTamaño() < p2.getTamaño()) {
                        comparison = -1;
                    }
                    return comparison;
                });
                return resultList;
            }catch(NumberFormatException e){return new ArrayList<>();}
        }else if(orderBy.equals("peso_mayor_que") || orderBy.equals("peso_menor_que")){
                try{
                Float peso = Float.parseFloat(searchable);
                List <Bebida> resultList = new ArrayList<>();
                if(orderBy.equals("peso_menor_que"))
                    resultList.addAll(bebidaRepository.findByPesoLessThanEqual(peso));
                else
                    resultList.addAll(bebidaRepository.findByPesoGreaterThanEqual(peso));
                resultList.sort((p1, p2) -> {
                    int comparison = 0;
                    if (p1.getPeso() > p2.getPeso()) {
                        comparison = 1;
                    } else if (p1.getPeso() < p2.getPeso()) {
                        comparison = -1;
                    }
                    return comparison;
                });
                return resultList;
            }catch(NumberFormatException e){return new ArrayList<>();}
        }
        HashSet<Bebida> resultSet = new HashSet<>();

        List<Bebida> byNombre = bebidaRepository.findByNombreContaining(searchable);
        resultSet.addAll(byNombre);

        List<Bebida> byDescripcion = bebidaRepository.findByDescripcionContaining(searchable);
        resultSet.addAll(byDescripcion);

        List<Bebida> byVendedor = bebidaRepository.findByVendedor_nombre(searchable);
        resultSet.addAll(byVendedor);

        try {
            Long id = Long.parseLong(searchable);
            List<Bebida> byVendedorId = bebidaRepository.findByVendedor_Vendedorid(id);
            resultSet.addAll(byVendedorId);

            Bebida byId = bebidaRepository.findById(id).orElse(null);
            if(byId != null) resultSet.add(byId);
        } catch (NumberFormatException e) {}

        List<Bebida> resultList = new ArrayList<>(resultSet);
        resultList.sort((p1, p2) -> {
            int comparison = 0;
            switch (orderBy) {
                case "id":
                    comparison = p1.getItemid().compareTo(p2.getItemid());
                    break;
                case "nombre":
                    comparison = p1.getNombre().compareTo(p2.getNombre());
                    break;
                case "descripcion":
                    comparison = p1.getDescripcion().compareTo(p2.getDescripcion());
                    break;
                case "vendedor":
                    comparison = p1.getVendedor().getNombre().compareTo(p2.getVendedor().getNombre());
                    break;
                case "precio":
                    comparison = Float.compare(p1.getPrecio(), p2.getPrecio());
                    break;
                case "graduacionAlcoholica":
                    comparison = Float.compare(p1.getGraduacionAlcoholica(), p2.getGraduacionAlcoholica());
                    break;
                case "tamaño":
                    comparison = Float.compare(p1.getTamaño(), p2.getTamaño());
                    break;
                case "peso":
                    comparison = Float.compare(p1.getPeso(), p2.getPeso());
                    break;
                default:
                    comparison = p1.getItemid().compareTo(p2.getItemid());
                    break;
            }
            if (orderDirection.equals("DESC")) {
                comparison *= -1;
            }
            return comparison;
        });
        return resultList;
    }

    public Bebida getBebidaById(Long id) {
        return bebidaRepository.findById(id).orElse(null);
    }

    public Bebida updateBebida(Long id, String nombre, String descripcion, float precio, float graduacionAlcoholica, float tamaño, float peso, Iterable<String> categoria) {
        Bebida bebida = bebidaRepository.findById(id).orElse(null);
        if (bebida != null) {
            bebida.setNombre(nombre);
            bebida.setDescripcion(descripcion);
            bebida.setPrecio(precio);
            bebida.setGraduacionAlcoholica(graduacionAlcoholica);
            bebida.setTamaño(tamaño);
            bebida.setPeso(peso);
            if(categoria != null) {
                bebida.clearCategoria();
                bebida.addCategoria(categoriaService.getCategoriaByNombre("Bebida", Categoria.TipoCategoria.BEBIDA));
                for (String cat : categoria) {
                    bebida.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.BEBIDA));
                }
            }
            return bebidaRepository.save(bebida);
        }
        return null;
    }

    public void deleteBebida(Long id) {
        bebidaRepository.deleteById(id);
    }
}
