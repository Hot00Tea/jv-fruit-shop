package core.basesyntax;

import core.basesyntax.dataconverter.DataConverter;
import core.basesyntax.dataconverter.DataConverterImpl;
import core.basesyntax.filereader.FileReader;
import core.basesyntax.filereader.FileReaderImpl;
import core.basesyntax.filewriter.FileWriterImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.operationstrategy.OperationStrategy;
import core.basesyntax.operationstrategy.OperationStrategyImpl;
import core.basesyntax.reportgenerator.ReportGenerator;
import core.basesyntax.reportgenerator.ReportGeneratorImpl;
import core.basesyntax.shopservice.ShopService;
import core.basesyntax.shopservice.ShopServiceImpl;
import core.basesyntax.storage.Storage;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReaderImpl();
        List<String> inputReport = fileReader.read("src/main/java/resources/reportToRead.csv");

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);

        OperationStrategy operationStrategy = new OperationStrategyImpl();
        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(transactions);

        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String report = reportGenerator.getReport();

        FileWriterImpl fileWriter = new FileWriterImpl();
        fileWriter.write(report, "src/main/java/resources/reportToRead.csv");
    }
}
