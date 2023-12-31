package restaurant.bangtanPalace.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import restaurant.bangtanPalace.Repository.FoodRepository;
import restaurant.bangtanPalace.domain.Food;
import restaurant.bangtanPalace.exception.BadRequestException;
import restaurant.bangtanPalace.mapper.FoodMapper;
import restaurant.bangtanPalace.request.Food.FoodPostRequestBody;
import restaurant.bangtanPalace.request.Food.FoodPutRequestBody;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final S3Client s3Client;
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public Page<Food> listAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    public List<Food> listAllNonPageable() {
        return foodRepository.findAll();
    }

    public Food findByIdOrThrowBadRequestException(long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Food Id not found"));
    }

    @Transactional
    public Food save(FoodPostRequestBody foodPostRequestBody) throws IOException {
        // Convert Base64 image data to ByteBuffer
        byte[] imageBytes = java.util.Base64.getDecoder().decode(foodPostRequestBody.getImage());
        ByteBuffer imageByteBuffer = ByteBuffer.wrap(imageBytes);

        // Generate unique key for the image in Amazon S3
        String imageName = java.util.UUID.randomUUID().toString();

        // Upload the image to Amazon S3
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(imageName)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromByteBuffer(imageByteBuffer));

        // Save the Food object with the image URL in the database
        Food food = FoodMapper.INSTANCE.toFood(foodPostRequestBody);
        food.setImage("https://" + bucketName + ".s3.amazonaws.com/" + imageName);
        return foodRepository.save(FoodMapper.INSTANCE.toFood(foodPostRequestBody));
    }


    public void delete(long id) {
        foodRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(FoodPutRequestBody foodPutRequestBody) {
        Food savedFood = findByIdOrThrowBadRequestException(foodPutRequestBody.getId());
        Food food = FoodMapper.INSTANCE.toFood(foodPutRequestBody);
        food.setId(savedFood.getId());
        foodRepository.save(food);
    }
}
