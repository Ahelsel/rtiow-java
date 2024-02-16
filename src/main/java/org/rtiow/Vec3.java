package org.rtiow;

public class Vec3 {

    public float[] e = new float[3];
    public Vec3() {}

    public Vec3(float e0, float e1, float e2) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    public float x() { return e[0]; }
    public float y() { return e[1]; }
    public float z() { return e[2]; }

    public float r() { return this.x(); }
    public float g() { return this.y(); }
    public float b() { return this.z(); }

    public Vec3 add(Vec3 v) {
        return new Vec3(e[0] + v.e[0], e[1] + v.e[1], e[2] + v.e[2]);
    }
    public Vec3 add(float t) {
        return new Vec3(e[0] + t, e[1] + t, e[2] + t);
    }
    public Vec3 sub(Vec3 v) {
        return new Vec3(e[0] - v.e[0], e[1] - v.e[1], e[2] - v.e[2]);
    }
    public Vec3 sub(float t) {
        return new Vec3(e[0] - t, e[1] - t, e[2] - t);
    }
    public Vec3 mult(Vec3 v) {
        return new Vec3(e[0] * v.e[0], e[1] * v.e[1], e[2] * v.e[2]);
    }
    public Vec3 mult(float t) {
        return new Vec3(e[0] * t, e[1] * t, e[2] * t);
    }
    public Vec3 divide(Vec3 v) {
        return new Vec3(e[0] / v.e[0], e[1] / v.e[1], e[2] / v.e[2]);
    }
    public Vec3 divide(float t) {
        return new Vec3(e[0] / t, e[1] / t, e[2] / t);
    }
    public float at(int i) {
        return e[i];
    }
    public float length() {
        return (float) Math.sqrt(e[0]*e[0] + e[1]*e[1] + e[2]*e[2]);
    }
    public float squared_length() {
        return this.length() * this.length();
    }
    public void make_unit_vector() {
        float k = 1.0f / this.length();
        e[0] *= k;
        e[1] *= k;
        e[2] *= k;
    }
    public float dot(Vec3 v) {
        return e[0] * v.e[0] + e[1] * v.e[1] + e[2] * v.e[2];
    }
    public void set(Vec3 v) {
        e[0] = v.e[0];
        e[1] = v.e[1];
        e[2] = v.e[2];
    }
    public Vec3 cross(Vec3 v1) {
        return new Vec3(e[1] * v1.e[2] - e[2] * v1.e[1],
                        e[2] * v1.e[0] - e[0] * v1.e[2],
                        e[0] * v1.e[1] - e[1] * v1.e[0]);
    }
    public Vec3 unit_vector() {
        return this.divide(this.length());
    }

}
