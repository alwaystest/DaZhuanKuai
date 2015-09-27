package eric;

public class Event implements Comparable<Event>{
	private final Double time;
	private final Ball ball;
	private final Brick	brick;
	private final int count;
	private  final boolean isVertical;

	public Event(Double time, Ball ball, Brick brick, boolean isVertical) {
		this.time = time;
		this.ball = ball;
		this.brick = brick;
		this.isVertical = isVertical;
		if (ball == null) {
			count = -1;
		} else {
			this.count = ball.getCount();
		}
	}
	
	public boolean isValid(){
		return !(ball != null && this.count != ball.getCount());
	}

	@Override
	public int compareTo(Event e) {
		if(this.time < e.time)
			return -1;
		if(this.time > e.time)
			return +1;
		return 0;
	}

	public Ball getBall() {
		return ball;
	}

	public Brick getBrick() {
		return brick;
	}

	public Double getTime() {
		return time;
	}

	public boolean isVertical() {
		return isVertical;
	}
}
