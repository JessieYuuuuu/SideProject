package tw.jessie.sideproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.jessie.sideproject.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    //擁有基本SQL語法
}
