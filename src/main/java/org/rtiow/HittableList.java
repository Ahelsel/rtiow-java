package org.rtiow;

import java.util.List;

public class HittableList extends Hittable {
    int list_size;
    List<Hittable> list;
    public HittableList(List<Hittable> list, int list_size) {
        this.list = list;
        this.list_size = list_size;
    }
    public HittableList() {}
    public boolean hit(Ray r, float t_min, float t_max, HitRecord rec) {
        HitRecord temp_rec = new HitRecord();
        boolean hit_anything = false;
        float closest_so_far = t_max;
        for (Hittable hittable : hittableList) {
            if (hittable.hit(r, t_min, closest_so_far, temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.t;
                rec = temp_rec;
            }
        }
        return hit_anything;
    }
}
