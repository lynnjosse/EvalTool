package com.lynnjosse.EvalTool.models.dao;


import com.lynnjosse.EvalTool.models.Evaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EvaluationDao extends CrudRepository<Evaluation, Integer> {
}
