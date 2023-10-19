package com.joo.everyletter_back.common.repository;

import com.joo.everyletter_back.common.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
