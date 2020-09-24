package com.stylefeng.guns.modular.system.consumer;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.QRCodeUtils;
import com.stylefeng.guns.modular.system.dao.ClientUserMapper;
import com.stylefeng.guns.modular.system.model.ClientUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

@Component
@Slf4j
public class UserPublicityImgConsumer implements CommandLineRunner {

    @Autowired
    private GunsProperties properties;
    @Autowired
    private ClientUserMapper userMapper;

    private ArrayBlockingQueue<Long> USER_PUBLICITY_QUEUE = new ArrayBlockingQueue<>(5000);

    @Override
    public void run(String... args) throws Exception {
        String filePath = properties.getWebServerPath() + "/web-imgs/";
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Long userId = null;
                    try {
                        userId = USER_PUBLICITY_QUEUE.take();
                        String day = DateUtil.getDays();
                        String qrFileName = day + "/" + UUID.randomUUID().toString() + ".png";
                        String content = properties.getH5LoginUrl() + "?code=" + userId;
                        QRCodeUtils.createQR(content, filePath, qrFileName, 300, 300);
                        String tg1FileName = day + "/" + UUID.randomUUID().toString() + ".jpg";
                        String tg2FileName = day + "/" + UUID.randomUUID().toString() + ".jpg";
                        //推广图1
                        QRCodeUtils.markImgMark(filePath + qrFileName,
                                properties.getWebServerPath() + "/images/tg1-small.jpg",
                                filePath + tg1FileName,
                                755, 1425,
                                280, 280);
                        //推广图2
                        QRCodeUtils.markImgMark(filePath + qrFileName,
                                properties.getWebServerPath() + "/images/tg2-small.jpg",
                                filePath + tg2FileName,
                                60, 1475,
                                280, 280);

                        ClientUser user = userMapper.selectById(userId);
                        user.setQrImg(qrFileName);
                        user.setTgImg1(tg1FileName);
                        user.setTgImg2(tg2FileName);
                        userMapper.updateById(user);
                    } catch (Exception e) {
                        log.error("user create publicity img error userId:{}", userId, e);
                    }
                }


            }
        }).start();
    }

    public void createPublicityImg(Long userId) throws InterruptedException {
        this.USER_PUBLICITY_QUEUE.put(userId);
    }
}
