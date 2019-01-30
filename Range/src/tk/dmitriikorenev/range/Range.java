package tk.dmitriikorenev.range;

public class Range {
    private static final double EPSILON = 1e-10;
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = Math.min(from, to);
        this.to = Math.max(from, to);
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isNumberInside(double x) {
        return x - from >= -EPSILON && to - x >= -EPSILON;
    }

    public boolean isRangeInside(Range range) {
        return this.isNumberInside(range.getFrom()) && this.isNumberInside(range.getTo());
    }

    public boolean isIntersect(Range range) {
        return (this.isNumberInside(range.getFrom()) || this.isNumberInside(range.getTo()) || range.isNumberInside(this.getFrom()) || range.isNumberInside(this.getTo()));
    }

    public Range getIntersection(Range range) {
        if (!this.isIntersect(range)) {
            return null;
        }
        return new Range(Math.max(this.getFrom(), range.getFrom()), Math.min(this.getTo(), range.getTo()));
    }

    public Range[] getUnion(Range range) {
        if (this.isIntersect(range)) {
            return new Range[]{new Range(Math.min(this.getFrom(), range.getFrom()), Math.max(this.getTo(), range.getTo()))};
        }
        return new Range[]{this, range};
    }

    public Range[] getDifference(Range range) {
        if (this.isRangeInside(range)) {
            return new Range[]{new Range(this.getFrom(), range.getFrom()), new Range(range.getTo(), this.getTo())};
        } else if (range.isRangeInside(this)) {
            return null;
        } else if (!this.isIntersect(range)) {
            return new Range[]{this};
        } else if (this.isNumberInside(range.getFrom())) {
            return new Range[]{new Range(this.getFrom(), range.getFrom())};
        } else {
            return new Range[]{new Range(this.getTo(), range.getTo())};
        }
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }
}
