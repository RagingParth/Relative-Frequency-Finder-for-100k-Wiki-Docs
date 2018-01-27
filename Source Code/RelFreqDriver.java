import java.io.*;
import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RelFreqDriver
{
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
    {
        Job j = Job.getInstance(new Configuration());
        j.setJarByClass(this.getClass());
        j.setJobName("Relative Frequency Finder");

        FileInputFormat.addInputPath(j, new Path(args[0]));
        FileOutputFormat.setOutputPath(j, new Path("temp_output"));

        j.setMapperClass(RelFreqMapper.class);
        j.setReducerClass(RelFreqReducer.class);
        j.setCombinerClass(AdjacentWordReducer.class);
        j.setPartitionerClass(AdjacentWordPartitioner.class);
        j.setNumReduceTasks(3);

        j.setOutputKeyClass(AdjacentWord.class);
        j.setOutputValueClass(IntWritable.class);
        j.waitForCompletion(true);

        Job j2 = Job.getInstance(new Configuration());
        j2.setJarByClass(RelFreqDriver.class);
        j2.setJobName("Relative Frequency Finder");

        j2.setSortComparatorClass(Descender.class);
        FileInputFormat.addInputPath(j2, new Path("temp_output"));
        FileOutputFormat.setOutputPath(j2, new Path(args[1]));

        j2.setMapperClass(RelFreqMapper2.class);
        j2.setReducerClass(RelFreqReducer2.class);
        j2.setNumReduceTasks(1);

        j2.setOutputKeyClass(DoubleWritable.class);
        j2.setOutputValueClass(AdjacentWord.class);
        System.exit(j2.waitForCompletion(true) ? 0 : 1);
    }
}
