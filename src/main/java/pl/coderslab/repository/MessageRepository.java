package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findAllByChannelId(long channelId);
}
