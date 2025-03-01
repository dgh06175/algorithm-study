import java.util.*;

class UserSolution {
	class Road {
		int startIndex, endIndex, cost;
		Road(int s, int e, int c) {
			this.startIndex = s;
			this.endIndex = e;
			this.cost = c;
		}
	}
	
	class State {
		int start, end, cost;
		State(int s, int e, int c) {
			this.start = s;
			this.end = e;
			this.cost = c;
		}
	}
	
	int index;
	List<List<Road>> roads = new ArrayList<>();
	Map<Integer, Integer> cityIndex = new HashMap<>();
	int v;
	int[][] dist;
	
	public int init(int N, int sCity[], int eCity[], int mCost[]) {
		index = 0;
		roads.clear();
		for(int i = 0; i < 602; i++) {
			roads.add(new ArrayList<>());
		}
		cityIndex.clear();
		for(int i = 0; i < N; i++) {
			int s = getIndex(sCity[i]);
			int e = getIndex(eCity[i]);
			roads.get(s).add(new Road(s, e, mCost[i]));
		}
		v = cityIndex.size();
		dist = new int[v][v];
		for(int i = 0; i < v; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0;
		}
		
		for(int i = 0; i < v; i++) {
			findAllMinDist(i);
		}
		
		return v;
	}
	
	private int getIndex(int city) {
		if (cityIndex.containsKey(city)) {
			return cityIndex.get(city);
		}
		cityIndex.put(city, index);
		return index++;
	}
	
	private void findAllMinDist(int s) {
		Queue<State> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));
		State start = new State(s, s, 0);
		pq.offer(start);
		
		while(!pq.isEmpty()) {
			State state = pq.poll();
			int now = state.end;
			for(Road road: roads.get(now)) {
				int next = road.endIndex;
				int nextdist = state.cost + road.cost;
				if (nextdist < dist[s][next]) {
					dist[s][next] = nextdist;
					pq.add(new State(s, next, nextdist));
				}
			}
		}
	}

	public void add(int sCity, int eCity, int mCost) {
		sCity = getIndex(sCity);
		eCity = getIndex(eCity);
		roads.get(sCity).add(new Road(sCity, eCity, mCost));
		
		for(int i = 0; i < v; i++) {
			for(int j = 0; j < v; j++) {
				if (dist[i][sCity] + mCost + dist[eCity][j] < dist[i][j]) {
					dist[i][j] = dist[i][sCity] + mCost + dist[eCity][j];
				}
			}
		}
	}

	public int cost(int mHub) {
		mHub = getIndex(mHub);
		int sum = 0;
		for(int i = 0; i < v; i++) {
			sum += dist[mHub][i] + dist[i][mHub];
		}
		return sum;
	}
}