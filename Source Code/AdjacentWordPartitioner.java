import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class AdjacentWordPartitioner extends Partitioner<AdjacentWord, IntWritable>
{
    @Override
    public int getPartition(AdjacentWord twoWords, IntWritable intWritable, int numPartitions)
	{
        return (twoWords.getWord().hashCode() & Integer.MAX_VALUE ) % numPartitions;
    }
}