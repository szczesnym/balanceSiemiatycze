package com.oerlemans.siemiatycze.balance.physical;

import com.pi4j.io.serial.*;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Service
public class SerialController {
    private SerialConfig serialConfig;
    private final Serial serial = SerialFactory.createInstance();
    private static SerialController instance;
    private String assciBuffer;
    private String hexBuffer;


    private SerialController() {
        serialConfig = new SerialConfig();
        try {
            serialConfig.device(SerialPort.getDefaultPort())
                    .baud(Baud._38400)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);
            serial.addListener((SerialDataEventListener) (SerialDataEvent event) -> {
                // NOTE! - It is extremely important to read the data received from the
                // serial port.  If it does not get read from the receive buffer, the
                // buffer will continue to grow and consume memory.
                
                // print out the data received to the console
                try {
                    assciBuffer = "";
                    hexBuffer = "";
                    assciBuffer = event.getHexByteString();
                    hexBuffer = event.getAsciiString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serial.open(serialConfig);
        } catch (Exception ex) {
            System.out.println("Serial init failed:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    @Bean
    public static SerialController getInstance() {
        if (instance == null) {
            synchronized (SerialController.class) {
                instance = new SerialController();
            }
        }
        return instance;
    }

    public String getAssciBuffer() {
        return assciBuffer;
    }

    public String getHexBuffer() {
        return hexBuffer;
    }
}
