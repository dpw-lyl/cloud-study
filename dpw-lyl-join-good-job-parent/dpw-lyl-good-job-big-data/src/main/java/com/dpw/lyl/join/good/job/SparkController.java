package com.dpw.lyl.join.good.job;

import lombok.Value;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 */
@SpringBootApplication
@RestController
public class SparkController {
  //  @Value("${spark.master:313}")
    private String sparkMaster;

  //  @Value("${spark.app.name:31313}")
    private String appName;

  //  @Value("${hdfs.input.path:31313}")
    private String hdfsInputPath;

    public static void main(String[] args) {
        SpringApplication.run(SparkController.class, args);

        List<Integer> objects = new ArrayList<>();

        for (int i = 0; i < 17; i++) {
            objects.add(i);
        }


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            if(i==6){
                executorService.shutdownNow();
            }
        }



    }

    @GetMapping("/process-data")
    public String processData() {
        SparkConf sparkConf = new SparkConf()
                .setAppName(appName)
                .setMaster(sparkMaster);

        SparkSession spark = SparkSession.builder()
                .config(sparkConf)
                .getOrCreate();

        // Load data from HDFS
        Dataset<Row> df = spark.read().text(hdfsInputPath);

        // Perform some operations on the DataFrame
        df.createOrReplaceTempView("data");
        Dataset<Row> result = spark.sql("SELECT * FROM data WHERE data.value LIKE '%some_pattern%'");

        // Convert the result to RDD and count the number of records
        JavaRDD<String> rdd = result.toJSON().javaRDD();
        long count = rdd.count();

        spark.stop();


        List<Integer> list = Arrays.asList(1);


        return "Processed data. Total records: " + count;
    }
}
