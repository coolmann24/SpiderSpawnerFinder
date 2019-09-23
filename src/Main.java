import java.util.ArrayList;
import java.util.Random;

public class Main
{
	private static Random baserand = new Random();
	public static ArrayList<Vec3i> spawners = new ArrayList<Vec3i>();
	
	public static void getSpawnersInChunk(long seed, int x, int z)
	{
		baserand.setSeed(seed);
		
		long j = baserand.nextLong();
		long k = baserand.nextLong();
		
		baserand.setSeed((x*j) ^ (z*k) ^ seed);
		
		baserand.nextInt();
		
		if(baserand.nextDouble() < .004D && baserand.nextInt(80) < Math.max(Math.abs(x), Math.abs(x)))
		{
			StructureMineshaftPieces.startBuild(baserand, x, z);
		}
	}
	
	public static void main(String[] args)
	{
		
		
		/*for(Vec3i v : spawners)
		{
			//System.out.println("X: " + v.getX() + " Y: " + v.getY() + " Z: " + v.getZ());
		}
		
		//ArrayList<Vec3i> list = new ArrayList<Vec3i>();
		/*for(int i = -1600; i <= 1600; i++)
		{
			for(int j = -1600; j <= 1600; j++)
			{
				for(Vec3i v : spawners)
				{
					if(Math.sqrt((double)((i-v.getX())*(i-v.getX())+(j-v.getZ())*(j-v.getZ())))<16)
					{
						list.add(v);
					}
					
				}
				
				if(list.size() >= 4)
				{
					System.out.println("X: " + i  + " Z: " + j + " Number: " + list.size());
				}
				list.clear();
			}
		}*/
		
		
		for(int i = -1250; i < 1250; i++)
		{
			for(int j = -1250; j < 1250; j++)
			{
				getSpawnerList(i*50 - 33, i*50 + 33, j*50 - 33, j*50 + 33, -4172144997902289642L, baserand);
				
				for(Vec3i v : spawners)
				{
					int count = 0;
					for(Vec3i b : spawners)
					{
						if((v.getX()-b.getX())*(v.getX()-b.getX())+(v.getZ()-b.getZ())*(v.getZ()-b.getZ())<484 && v != b)
						{
							count++;
						}
					}
					
					if(count >= 10)
					{
						System.out.println("X: " + v.getX()  + " Z: " + v.getZ() + " Number: " + count);
					}
				}
				
				spawners.clear();
			}
		}
		
		
	}
	
	public static void getSpawnerList(int xchunkmin, int xchunkmax, int zchunkmin, int zchunkmax, long seed, Random rand)
	{
		for(int x = xchunkmin; x <= xchunkmax; x++)
		{
			for(int z = zchunkmin; z <= zchunkmax; z++)
			{
				getSpawnersInChunk(seed, x, z);
			}
		}
	}
}
