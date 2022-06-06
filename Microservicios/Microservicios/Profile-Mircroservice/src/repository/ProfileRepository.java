package com.acme.elegant.repository;

import com.acme.elegant.model.Post;
import com.acme.elegant.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

}
