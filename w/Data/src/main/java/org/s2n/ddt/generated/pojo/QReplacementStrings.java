package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QReplacementStrings is a Querydsl query type for QReplacementStrings
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QReplacementStrings extends com.mysema.query.sql.RelationalPathBase<QReplacementStrings> {

    private static final long serialVersionUID = -1062347223;

    public static final QReplacementStrings ReplacementStrings = new QReplacementStrings("Replacement_Strings");

    public final NumberPath<Integer> appId = createNumber("appId", Integer.class);

    public final NumberPath<Integer> encrypted = createNumber("encrypted", Integer.class);

    public final StringPath foreignId = createString("foreign_id");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath level = createString("level");

    public final StringPath name = createString("Name");

    public final StringPath value = createString("Value");

    public final com.mysema.query.sql.PrimaryKey<QReplacementStrings> primary = createPrimaryKey(id);

    public QReplacementStrings(String variable) {
        super(QReplacementStrings.class, forVariable(variable), "null", "Replacement_Strings");
    }

    @SuppressWarnings("all")
    public QReplacementStrings(Path<? extends QReplacementStrings> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "Replacement_Strings");
    }

    public QReplacementStrings(PathMetadata<?> metadata) {
        super(QReplacementStrings.class, metadata, "null", "Replacement_Strings");
    }

}

