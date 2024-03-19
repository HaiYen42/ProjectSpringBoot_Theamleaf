package chinh.nguyen.model;

import com.sun.istack.NotNull;
import org.aspectj.bridge.IMessage;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "category",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ko được để trống trường này")
    private String name;
    @NotNull
    private boolean status =true;
    @Lob
    private String image = "https://firebasestorage.googleapis.com/v0/b/springboot-theamleaf.appspot.com/o/flower2.jpg?alt=media&token=a1304a7c-0652-42a1-a0d6-2255395dfa97";

    public Category() {
    }

    public Category(Long id, String name, boolean status, String image) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
