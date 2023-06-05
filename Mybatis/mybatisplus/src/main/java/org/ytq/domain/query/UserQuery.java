package org.ytq.domain.query;

import lombok.Data;
import org.ytq.domain.User;

@Data
public class UserQuery extends User {
    private Integer upper_age;
}
