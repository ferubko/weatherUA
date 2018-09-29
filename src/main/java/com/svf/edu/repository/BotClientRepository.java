package com.svf.edu.repository;

import com.svf.edu.vo.BotClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by stepanferubko
 */
@Repository
public interface BotClientRepository extends JpaRepository<BotClient, Long>{
    BotClient findByChatId(long chatId);
}
