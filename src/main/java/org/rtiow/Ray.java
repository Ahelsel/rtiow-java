package org.rtiow;

public class Ray {
    Vec3 A;
    Vec3 B;
    Ray() {}
    Ray(Vec3 a, Vec3 b) {
        A = a;
        B = b;
    }
    Vec3 origin() {
        return A;
    }
    Vec3 direction() {
        return B;
    }
    Vec3 point_at_parameter(float t) {
        return A.add(B.mult(t));
    }
}
