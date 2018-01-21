package org.s2n.ddt.generated.pojo;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestReport is a Querydsl query type for QTestReport
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestReport extends com.mysema.query.sql.RelationalPathBase<QTestReport> {

    private static final long serialVersionUID = -523041921;

    public static final QTestReport TestReport = new QTestReport("TestReport");

    public final StringPath createdBy = createString("CreatedBy");

    public final DatePath<java.sql.Date> createdDateTime = createDate("CreatedDateTime", java.sql.Date.class);

    public final StringPath reportDetails = createString("ReportDetails");

    public final NumberPath<Integer> testReportID = createNumber("TestReportID", Integer.class);

    public final NumberPath<Integer> testRunID = createNumber("TestRunID", Integer.class);

    public final StringPath updatedBy = createString("UpdatedBy");

    public final DatePath<java.sql.Date> updatedDateTime = createDate("UpdatedDateTime", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QTestReport> primary = createPrimaryKey(testReportID);

    public QTestReport(String variable) {
        super(QTestReport.class, forVariable(variable), "null", "TestReport");
    }

    @SuppressWarnings("all")
    public QTestReport(Path<? extends QTestReport> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "TestReport");
    }

    public QTestReport(PathMetadata<?> metadata) {
        super(QTestReport.class, metadata, "null", "TestReport");
    }

}

