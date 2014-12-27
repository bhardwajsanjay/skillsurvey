package au.com.redbackconsulting.skillsurvey.persistence.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * The persistent class for the function database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name=DBQueries.GET_FUNCTION_BY_ID, query ="SELECT f FROM Function f where f.idfunction =:id")
	
})
//@NamedQuery(name="Function.findAll", query="SELECT f FROM Function f")
@Table(name = "functiontable")
public class Function implements Serializable, IDBEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idfunction;

	private String description;
@Column(name="name", nullable=false, unique=true)
	private String name;

	//bi-directional many-to-one association to Individual
 
	@OneToMany(mappedBy="function", fetch=FetchType.EAGER)
	private List<Individual> individuals;



	public Function() {
	}

	public long getIdfunction() {
		return this.idfunction;
	}

	public void setIdfunction(long idfunction) {
this.idfunction = idfunction;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


//	public List<Individual> getIndividuals() {
//		return this.individuals;
//	}
//
//	public void setIndividuals(List<Individual> individuals) {
//		this.individuals = individuals;
//	}

//	public Individual addIndividual(Individual individual) {
//		getIndividuals().add(individual);
//		individual.setFunction(this);
//
//		return individual;
//	}
//
//	public Individual removeIndividual(Individual individual) {
//		getIndividuals().remove(individual);
//		individual.setFunction(null);
//
//		return individual;
//	}
//	

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="FunctionOccupations"
		, joinColumns={
			@JoinColumn(name="functionid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="occupationid")
			}
		)
private  List<Occupation> occupations;

	public List<Occupation> getOccupations() {
		return occupations;
	}

	public void setOccupations(List<Occupation> occupations) {
		this.occupations = occupations;
	} 
	public static Comparator<Function> functionNameComparator 
    = new Comparator<Function>() {

public int compare(Function function1, Function function2) {

String functionName1 = function1.getDescription().toUpperCase();
String functionName2 = function2.getDescription().toUpperCase();

//ascending order
return functionName1.compareTo(functionName2);

//descending order
//return fruitName2.compareTo(fruitName1);
}

};
}