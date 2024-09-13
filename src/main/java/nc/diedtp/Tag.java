/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.util.HashSet;

public class Tag {
    private static HashSet<Tag> allTags = new HashSet();
    public static final Tag tagComidas = new Tag("Comida", true);
    public static final Tag tagBebidas = new Tag("Bebida", false);
    private static boolean init = false;
    private static int next_id = 0;
    private int id;
    //Si no es comida es bebida
    private boolean esComida;
    private String tag;
    private String descripcion;
    public static void tagsInit(){
        allTags.add(tagComidas);
        allTags.add(tagBebidas);
        init = true;
    }
    public static Tag getTag(String tagname){
        if(!init) {return null;}
        for(Tag tagBusqueda: allTags){
            if(tagBusqueda.toString().equals(tagname)){
                return tagBusqueda;
            }
        }
        return null;
    }
    public static Tag createTag(String tag, boolean esComida){
        if(!init) {return null;}
        Tag aux = getTag(tag);
        if(aux == null){
            aux =new Tag(tag, esComida);
            allTags.add(aux);
            return aux;
        }
        if(aux.esComida == esComida){
        return aux;
        }
        return null;
        
    }
    public static Tag createTag(String tag, boolean esComida, String descripcion){
        Tag thisTag = createTag(tag,esComida);
        thisTag.descripcion = descripcion;
        return thisTag;
    }
    public static boolean deleteTag(String tag){
        if(tag.equals(tagComidas.toString()) || tag.equals(tagBebidas.toString())) return false;
        Tag removeTag = getTag(tag);
        if(removeTag == null) return false;
        allTags.remove(removeTag);
        return true;
    }
    
    private Tag(String tag,boolean esComida){
        this.tag = tag;
        this.id = next_id;
        ++next_id;
        this.descripcion = new String();
        this.esComida = esComida;
    }
    @Override
    public String toString(){
        return tag;
    }
    public boolean getEsComida(){
        return esComida;
    }
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void modifyDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
