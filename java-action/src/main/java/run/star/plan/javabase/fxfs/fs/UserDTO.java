package run.star.plan.javabase.fxfs.fs;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author hecs
 * @Date 2021/11/12 20:52
 */
@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String name;

    public UserDTO() {
    }

    private UserDTO(Integer id) {
        this.id = id;
    }

    public UserDTO(String name) {
        this.name = name;
    }

    public UserDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
