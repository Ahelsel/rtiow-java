package org.rtiow;
import java.util.ArrayList;
import java.util.List;

// https://github.com/rogerallen/raytracinginoneweekendincuda/blob/master/main.cc

public class Main {
    public Vec3 color(Ray r, List<Hittable> world, int depth) {
        // @TODO: Implement
    }
    public List<Hittable> randomScene() {
        // @TODO: Implement

    }
    public static void main(String[] args) {
        // @TODO: Implement
        int nx = 1200;
        int ny = 800;
        int ns = 10;
        // @TODO: System.out.println();
        List<Hittable> list = new ArrayList<>();
        list.add(new Sphere(new Vec3(0F,0F,-1F), 0.5F, new Lambertian(new Vec3(0.1F, 0.2F,0.5F))));
        // add more items to list

        HittableList world = new HittableList(list, 5);

        Vec3 lookFrom = new Vec3(13F, 2F, 3F);
        Vec3 lookAt =  new Vec3(0F, 0F, 0F);
        float dist_to_focus = 10.0F;
        float aperture = 0.1F;

        Camera cam = new Camera(lookFrom, lookAt, new Vec3(0F,1F,0F), 20F, (float)nx/(float)ny, aperture, dist_to_focus);

        // @TODO: finish

    }
}