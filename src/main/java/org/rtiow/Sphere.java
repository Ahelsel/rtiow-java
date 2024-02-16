package org.rtiow;

public class Sphere extends Hittable {
    Vec3 center;
    float radius;
    Material mat;

    public Sphere() {}
    public Sphere(Vec3 center, float radius, Material mat) {
        this.center = center;
        this.radius = radius;
        this.mat = mat;
    }
    public boolean hit(Ray r, float t_min, float t_max, HitRecord rec) {
        Vec3 oc = r.origin().sub(center);
        float a = r.direction().squared_length();
        float half_b = oc.dot(r.direction());
        float c = oc.squared_length() - radius*radius;
        float discriminant = half_b*half_b - a*c;
        if (discriminant > 0) {
            float root = (float)Math.sqrt(discriminant);
            float temp = (-half_b - root) / a;
            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.point_at_parameter(rec.t);
                rec.normal = rec.p.sub(center).divide(radius);
                rec.mat = mat;
                return true;
            }
            temp = (-half_b + root) / a;
            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.point_at_parameter(rec.t);
                rec.normal = rec.p.sub(center).divide(radius);
                rec.mat = mat;
                return true;
            }
        }
        return false;
    }

}
