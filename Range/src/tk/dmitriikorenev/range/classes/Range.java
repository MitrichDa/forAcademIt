package tk.dmitriikorenev.range.classes;

public class Range {
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

    public boolean isInside(double x) {
        return x >= from && x <= to;
    }

    private boolean isIntersect(Range range) {
        return (this.from < range.to && this.to > range.from);
    }

    public Range getIntersection(Range range) {
        if (this.isIntersect(range)) {
            return new Range(Math.max(this.from, range.from), Math.min(this.to, range.to));
        }
        return null;
    }

    public Range[] getDifference(Range range) {
        if (!this.isIntersect(range)) {
            return new Range[]{new Range(this.from, this.to)};
        } else if (range.from > this.from && range.to < this.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
        } else if (this.from >= range.from && this.to <= range.to) {
            return new Range[]{};
        } else if (range.from > this.from) {
            return new Range[]{new Range(this.from, range.from)};
        } else {
            return new Range[]{new Range(this.to, range.to)};
        }
    }

    public Range[] getUnion(Range range) {
        if (this.isIntersect(range) || this.from == range.to || this.to == range.from) {
            return new Range[]{new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))};
        }
        return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }
}
