package co.edureka.edurekasession2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Code here
        String action = intent.getAction(); // Get the Action First
        String packageName = intent.getData().getSchemeSpecificPart();

        if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
            Toast.makeText(context,"Package is Added: "+packageName,Toast.LENGTH_LONG).show();
        }

        if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){

            Toast.makeText(context,"Package is Removed: "+packageName,Toast.LENGTH_LONG).show();

            /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("App Uninstalled");
            builder.setMessage("App is Uninstalled "+packageName);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();*/

            // Do whatever your requirement is
        }

        if(action.equals(Intent.ACTION_PACKAGE_CHANGED)){
            Toast.makeText(context,"Package is Changed: "+packageName,Toast.LENGTH_LONG).show();
        }

    }
}
