package org.example.model;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
public class Product {
    @Id
    protected String id;

    @Indexed(unique = true, name = "productId.index", background = true)
    @NonNull
    protected String productId;

    protected String productName;

    protected Meta meta;
}
