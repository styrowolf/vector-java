package vector;

import java.lang.Math;

public class Vector {
    // a basic 2 dimensional vector, represented by:
    public double magnitude; // a magnitude
    public double angle; // and an angle in radians

    public Vector(double m, double a) {
        this.magnitude = m;
        this.angle = a % (2 * Math.PI);
    }
    
    public static Vector new_with_radians(double m, double a) {
        return new Vector(m, a);
    }

    public static Vector new_with_degrees(double m, double a) {
        return new Vector(m, a / 180 * Math.PI);
    }

    // get the reciprocal of a vector
    public Vector recip() {
        return new Vector(this.magnitude, this.angle + Math.PI);
    }

    public Vector get_x_component() {
        double x = this.magnitude * Math.cos(this.angle);
        double a;
        if (x < 0) {
            a = Math.PI;
        } else {
            a = 0.0;
        }
        return new Vector(x, a);
    }

    public Vector get_y_component() {
        double y = this.magnitude * Math.sin(this.angle);
        double a;
        if (y < 0) {
            a = Math.PI * 3 / 2;
        } else {
            a = Math.PI / 2;
        }
        return new Vector(y, a);
    }

    public Vector[] get_components() {
        return new Vector[] {get_x_component(), get_y_component()};
    }

    // for calculating the angle of a vector whose 
    // x and y components are respectively denoted by x and y
    public static double angle(double x, double y) {
        if (x < 0) {
            return Math.atan(y/x) + Math.PI;
        } else {
            if (y > 0) {
                return Math.atan(y/x);
            } else {
                return Math.atan(y/x) + 2 * Math.PI;
            }
        }
    }
    
    // is there an interface I can implement so that 
    // two vectors can be added just with the + operator?
    // in Rust, this is done via implementing the std::ops::Add trait
    
    // adding two vectors 
    public static Vector add(Vector lhs, Vector rhs) {
        double x1 = lhs.magnitude * Math.cos(lhs.angle);
        double y1 = lhs.magnitude * Math.sin(lhs.angle);

        double x2 = rhs.magnitude * Math.cos(rhs.angle);
        double y2 = rhs.magnitude * Math.sin(rhs.angle);

        double x_comp = x1 + x2;
        double y_comp = y1 + y2;

        double magnitude = Math.hypot(x_comp, y_comp);
        double angle = angle(x_comp, y_comp);

        return new Vector(magnitude, angle);
    }

    // since subtracting a vectors is actually 
    // adding its reciprocal
    public static Vector subtract(Vector lhs, Vector rhs) {
        return add(lhs, rhs.recip());
    }
}