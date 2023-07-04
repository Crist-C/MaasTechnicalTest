package com.ccastro.maas.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    icon: ImageVector? = null,//Icons.Default.ArrowForward
    shape: CornerBasedShape = ShapeDefaults.ExtraLarge,
    enable: Boolean = true
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = { onClick() },
        colors = colors,
        shape = shape,
        enabled = enable
    ){
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.padding(2.dp),
            text = text,
            //style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultButtonPreview(){
    MaasTheme() {
        DefaultButton(text = "AGREGAR TARJETA", onClick = { /*TODO*/ })
    }
}