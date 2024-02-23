package com.example.camerafunapp.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.camerafunapp.domain.Classification
import com.example.camerafunapp.domain.LandmarkClassifier

class LandmarkImageAnalyser (
    private val classifier: LandmarkClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(321, 321)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)

        }
        frameSkipCounter++
        image.close()
    }

}