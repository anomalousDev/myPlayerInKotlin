package com.serbladev.myplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serbladev.myplayer.databinding.ViewMediaItemBinding
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_media_item.view.*

// Aquí el MediaAdapter trabajará con item (privado porque no se va a usar nunca desde fuera), que es un listado de MediaItem
class MediaAdapter(private val items: List<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {


//2º: Implementamos los métodos obligatorios de nuestro MediaAdapter.Viewholder

    // Cada vez que queramos crear una nueva celda, vendrá al método onCreateViewHolder:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Aquí estamos inflando el layout de cada item (usando la función de extension inflate que hemos creado)
        val view = parent.inflate(R.layout.view_media_item)
        return ViewHolder(view)
    }

    // Cuando no necesite crear las celdas porque ya pueda reciclar las que "desaparecen" al hacer scroll, tirará de onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    // Aquí devolveremos el número de items que tiene este MediaAdapter
    override fun getItemCount(): Int = items.size

//1º: Creamos nuestro viewHolder. El contenedor donde va a pintarse la RecyclerView
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ViewMediaItemBinding.bind(view)

      /*  //Relacionamos código con su XML de la manera habitual con los findViewById
        private val title : TextView = view.findViewById(R.id.mediaTitle)
        private val thumb : ImageView = view.findViewById(R.id.mediaThumb)
        private val videoIndicator: ImageView = view.findViewById(R.id.mediaVideoIndicator)
      */


        //Este método recibirá un MediaItem y cogerá el título y lo pondrá en el title, y cogerá la imágen y la inflará en el ImageView
        fun bind(mediaItem: MediaItem){

            //Podemos evitar usar los findViewById mediante el ViewBinding, para ello hemos de poner binding.laIdQueSea.
            //Aunque en el XML se llame la id como media_title, en el código de KT puede ponerse mediaTitle y seguirá sabiendo a qué elemento te refieres

            //Aqui cargamos el texto del título en el TextView llamado title.
            binding.mediaTitle.text = mediaItem.title

            //Aquí cargamos una foto en el imageView llamado Thumb (usando la función de extension loadUrl que hemos creado)
            binding.mediaThumb.loadUrl(mediaItem.url)

            //Aquí estamos cambiando la visibilidad del videoIndicator a "visible" o "invisible" en función de si el mediaItem es de tipo PHOTO o VIDEO
            binding.mediaVideoIndicator.visibility= when (mediaItem.type) {

                //El when, en el caso de los enum, es capaz de saber en tiempo de compilación cuántos subtipos tiene. Por eso, pinchámndole con ALT+click y
                // dando a "Add remaining branches", él solo nos pintará todos los subtipos existentes, en este caso dentro de MediaItem
                MediaItem.Type.PHOTO -> View.GONE
                MediaItem.Type.VIDEO -> View.VISIBLE
               //No hace falta añadir el "else" por la razón antes dicha, ya que sabe cuántos tipos existen dentro de MediaItem
            }

            itemView.setOnClickListener{
                toast(mediaItem.title)
            }
        }
    }

}