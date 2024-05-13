package neptune.mixins.mixininterface;

import net.minecraft.util.math.Vec3i;
import org.joml.Vector3d;

public interface IVec3d {

    void setCoordinates(double x, double y, double z);

    default void setFromVec3i(Vec3i vec) {
        setCoordinates(vec.getX(), vec.getY(), vec.getZ());
    }

    default void setFromVector3d(Vector3d vec) {
        setCoordinates(vec.x, vec.y, vec.z);
    }

    void setXZCoordinates(double x, double z);

    void setYCoordinate(double y);
}
