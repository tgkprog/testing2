package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestReportTst is a Querydsl query type for QTestReportTst
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestReportTst extends com.mysema.query.sql.RelationalPathBase<QTestReportTst> {

    private static final long serialVersionUID = 199565782;

    public static final QTestReportTst TestReportTst = new QTestReportTst("TestReport_tst");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath reportDetails = createString("ReportDetails");

    public final NumberPath<Integer> testReportID = createNumber("TestReportID", Integer.class);

    public final NumberPath<Integer> testRunID = createNumber("TestRunID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestReportTst> primary = createPrimaryKey(testReportID);

    public QTestReportTst(String variable) {
        super(QTestReportTst.class, forVariable(variable), "null", "TestReport_tst");
    }

    @SuppressWarnings("all")
    public QTestReportTst(Path<? extends QTestReportTst> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestReport_tst");
    }

    public QTestReportTst(PathMetadata<?> metadata) {
        super(QTestReportTst.class, metadata, "null", "TestReport_tst");
    }

}

