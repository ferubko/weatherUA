package com.svf.edu.vo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stepanferubko
 */
@Entity
@Table
public class BotClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private long chatId;
    private String previousCommand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getPreviousCommand() {
        return previousCommand;
    }

    public void setPreviousCommand(String previousCommand) {
        this.previousCommand = previousCommand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BotClient botClient = (BotClient) o;

        if (chatId != botClient.chatId) return false;
        if (id != null ? !id.equals(botClient.id) : botClient.id != null) return false;
        if (clientName != null ? !clientName.equals(botClient.clientName) : botClient.clientName != null) return false;
        return previousCommand != null ? previousCommand.equals(botClient.previousCommand) : botClient.previousCommand == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (int) (chatId ^ (chatId >>> 32));
        result = 31 * result + (previousCommand != null ? previousCommand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BotClient{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", chatId=" + chatId +
                ", previousCommand='" + previousCommand + '\'' +
                '}';
    }
}
