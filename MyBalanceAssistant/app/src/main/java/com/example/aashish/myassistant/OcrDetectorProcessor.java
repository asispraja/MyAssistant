/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.aashish.myassistant;

import android.util.SparseArray;

import com.example.aashish.myassistant.ui.camera.GraphicOverlay;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

/**
 * A very simple Processor which receives detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> mGraphicOverlay;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        mGraphicOverlay = ocrGraphicOverlay;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        int status=0;
        mGraphicOverlay.clear();
//        String blocks = "";
//        String lines = "";
//        String words = "";
        SparseArray<TextBlock> items = detections.getDetectedItems();
        TextBlock item = null;
        for (int i = 0; i < items.size(); ++i) {
             item = items.valueAt(i);
//
//            status=0;
//
//            for (int index = 0; index < items.size(); index++) {
//                //extract scanned text blocks here
//                TextBlock tBlock = items.valueAt(index);
//                blocks = blocks + tBlock.getValue() + "\n" + "\n";
//                for (Text line : tBlock.getComponents()) {
//                    //extract scanned text lines here
//                    lines = lines + line.getValue() + "\n";
//                    for (Text element : line.getComponents()) {
//                        //extract scanned text words here
//                        words = words + element.getValue() + ", ";
//                        if ((words.matches("[0-9]+")) && (words.length() >= 16)) {
//                            status = 1;
//                            break;
//                        }
//                    }
//                }
//
//
//            }
//
//
       }
//        if (status == 1) {
            OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
            mGraphicOverlay.add(graphic);
//        }
//        else
//           mGraphicOverlay.clear();

    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        mGraphicOverlay.clear();
    }
}
