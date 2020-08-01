/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibtest;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class votes {
    
    @Id
    private Integer Id;
    private Integer PostId;
    private Integer VoteTypeId;
    private Integer UserId;
    private String CreationDate;

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setPostId(int PostId) {
        this.PostId = PostId;
    }

    public void setVoteTypeId(int VoteTypeId) {
        this.VoteTypeId = VoteTypeId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public void setCreationDate(String CreationDate) {
        this.CreationDate = CreationDate;
    }
    
    
    public int getId() {
        return Id;
    }
    
    public int getPostId() {
        return PostId;
    }
    
    public int getVoteTypeId() {
        return VoteTypeId;
    }

    public int getUserId() {
        return UserId;
    }

    public String getCreationDate() {
        return CreationDate;
    }
    
}

