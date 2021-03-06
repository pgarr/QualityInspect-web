package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Form;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FormDao extends JpaRepository<Form, Long> {

    List<Form> findByArchivedFalse();

    List<Form> findByArchivedFalseAndItem_Id(long itemId);

    @Override
    @EntityGraph(attributePaths = {"steps"})
    Optional<Form> findById(Long aLong);

    // this method needs proper name to query setArchived to true
    @Modifying(clearAutomatically = true)
    @Query("update Form f set f.archived = ?1 where f.id = ?2")
    void setFixedArchivedFor(boolean archived, long id);
}
