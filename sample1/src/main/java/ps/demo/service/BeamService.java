package ps.demo.service;


import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class BeamService {

//    @Autowired
//    private PipelineOptions pipelineOptions;
//
//    public List<String> readFile(String filePath) throws IOException {
//        TextIO.Read read = TextIO.read().from(filePath);
//        Pipeline pipeline = Pipeline.create(pipelineOptions);
//        List<String> lines = null;//pipeline.apply(read).get();
//        return lines;
//    }
//
//    public List<String> countWords(List<String> lines) {
////        return lines.stream()
////                .flatMap(line -> StreamSupport.stream(line.split("[^a-zA-Z']+"), false))
////                .map(String::toLowerCase)
////                .filter(word -> !word.isEmpty())
////                .collect(Collectors.toList());
//        return null;
//    }
//
//    public void writeFile(List<String> words, String outputPath) throws IOException {
//        Files.write(Paths.get(outputPath), words, StandardOpenOption.CREATE);
//    }
}
