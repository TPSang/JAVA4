
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "video")
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
    @NamedQuery(name = "Video.findById", query = "SELECT v FROM Video v WHERE v.id = :id"),
    @NamedQuery(name = "Video.findByTitle", query = "SELECT v FROM Video v WHERE v.title = :title"),
    @NamedQuery(name = "Video.findByHref", query = "SELECT v FROM Video v WHERE v.href = :href"),
    @NamedQuery(name = "Video.findByPoster", query = "SELECT v FROM Video v WHERE v.poster = :poster"),
    @NamedQuery(name = "Video.findByViews", query = "SELECT v FROM Video v WHERE v.views = :views"),
    @NamedQuery(name = "Video.findByShares", query = "SELECT v FROM Video v WHERE v.shares = :shares"),
    @NamedQuery(name = "Video.findByDescription", query = "SELECT v FROM Video v WHERE v.description = :description"),
    @NamedQuery(name = "Video.findByIsActive", query = "SELECT v FROM Video v WHERE v.isActive = :isActive")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "href")
    private String href;
    @Column(name = "poster")
    private String poster;
    @Basic(optional = false)
    @Column(name = "views")
    private int views;
    @Basic(optional = false)
    @Column(name = "shares")
    private int shares;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "isActive")
    private boolean isActive;
    @OneToMany(mappedBy = "video")
    private Collection<History> historyCollection;

    public Video() {
    }

    public Video(Integer id) {
        this.id = id;
    }

    public Video(Integer id, String title, String href, int views, int shares, String description, boolean isActive) {
        this.id = id;
        this.title = title;
        this.href = href;
        this.views = views;
        this.shares = shares;
        this.description = description;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
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
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Video[ id=" + id + " ]";
    }
    
}
