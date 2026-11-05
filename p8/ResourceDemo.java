class DemoResource implements AutoCloseable {
    private final String name;

    public DemoResource(String name) {
        this.name = name;
        System.out.println(name + " opened");
    }

    public void use() {
        System.out.println(name + " used");
    }

    @Override
    public void close() {
        System.out.println(name + " closed");
    }
}

public class ResourceDemo {
    public static void main(String[] args) {
        try {
            useResource();
        } catch (Exception exception) {
            // The original exception from the try block is reported.
            System.out.println("Original error: " + exception.getMessage());
        }
    }

    private static void useResource() throws Exception {
        // try-with-resources closes the resource even when use() fails.
        try (DemoResource resource = new DemoResource("File resource")) {
            resource.use();
            throw new Exception("Error while processing the resource");
        }
    }
}
