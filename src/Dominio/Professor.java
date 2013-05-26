/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Leticia
 */
@Entity
public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    
    
    @OneToMany
    @ElementCollection
    private List<Aula> grade;
    
    
    protected Professor(){}

    public Professor(String nome) {
        this.nome = nome;
        this.grade = new LinkedList<Aula>();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Professor[ id=" + id + " ]";
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the grade
     */
    public List<Aula> getGrade() {
        return grade;
    }

    public void adicionarAula(Aula aula) {
        
        if(aula == null){
            throw new IllegalStateException("Aula inválida.");
        }
        if(grade.contains(aula)){
            throw new IllegalStateException("Professor já contem esta aula em sua grade");
        }
        
        grade.add(aula);
        
    }

    public boolean  EhCompativelCom(List<Aula> aulas) {
        
        Collections.sort(this.grade, new AulaComparator());
        
        boolean ehCompativel = false;
        boolean ehIncompativel = false;
        //int itAulas = 0;

        while(!ehCompativel || !ehIncompativel){
            
            
            
        }
        
        return true;
        
        
    }
    
}
