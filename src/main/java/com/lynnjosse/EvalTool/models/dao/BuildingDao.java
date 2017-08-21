package com.lynnjosse.EvalTool.models.dao;

import com.lynnjosse.EvalTool.models.Building;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public interface BuildingDao extends CrudRepository<Building, Integer> {

    public List<Building> findByWard (Integer ward);
    public List<Building> findByStreetname(String streetname);
    public List<Building> findByWardAndStreetname (Integer ward, String streetname);

@Query(value = "select distinct ward from building" , nativeQuery = true)
List<Integer> findDistinctWards();

@Query(value = "select distinct streetname from building where ward= ?1" , nativeQuery = true)
List<String> findDistinctStreets(Integer wardNum);
}