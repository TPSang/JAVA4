
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "history")
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findById", query = "SELECT h FROM History h WHERE h.id = :id"),
    @NamedQuery(name = "History.findByViewedDate", query = "SELECT h FROM History h WHERE h.viewedDate = :viewedDate"),
    @NamedQuery(name = "History.findByIsLiked", query = "SELECT h FROM History h WHERE h.isLiked = :isLiked"),
    @NamedQuery(name = "History.findByLikeddDate", query = "SELECT h FROM History h WHERE h.likeddDate = :likeddDate")})
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "viewedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewedDate;
    @Basic(optional = false)
    @Column(name = "isLiked")
    private boolean isLiked;
    @Column(name = "likeddDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeddDate;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private User user;
    @JoinColumn(name = "videoId", referencedColumnName = "id")
    @ManyToOne
    private Video video;

    public History() {
    }

    public History(Integer id) {
        this.id = id;
    }

    public History(Integer id, Date viewedDate, boolean isLiked) {
        this.id = id;
        this.viewedDate = viewedDate;
        this.isLiked = isLiked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Date getLikeddDate() {
        return likeddDate;
    }

    public void setLikeddDate(Date likeddDate) {
        this.likeddDate = likeddDate;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public Video getVideoId() {
        return video;
    }

    public void setVideoId(Video videoId) {
        this.video = videoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.History[ id=" + id + " ]";
    }
    
}
