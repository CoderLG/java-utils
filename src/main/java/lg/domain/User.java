package lg.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


//@Data
@Entity
@Table(name="t_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class User {



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	//数据库中的对应字段 user_id
	private Integer userId;

	private Integer age;
	private String name;

	@Column(name = "gridset")
	//数据库中的对应字段 gridset
	private String gridSet;

	//数据库中的对应字段 map_default
	private Integer mapDefault = 0;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGridSet() {
		return gridSet;
	}

	public void setGridSet(String gridSet) {
		this.gridSet = gridSet;
	}

	public Integer getMapDefault() {
		return mapDefault;
	}

	public void setMapDefault(Integer mapDefault) {
		this.mapDefault = mapDefault;
	}

}
